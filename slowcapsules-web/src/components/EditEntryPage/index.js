import {NavLink, useLocation} from "react-router-dom";
import React, {useEffect, useRef, useState} from "react";
import EditorJS from "@editorjs/editorjs";
import EntryApi from "../../api/EntryApi";
import Header from "@editorjs/header";
import ImageTool from "@editorjs/image";
import SeriesApi from "../../api/SeriesApi";
import edjsHTML from "editorjs-html";

function EditEntryPage(){
    const location = useLocation();

    // return(
    //     <Editor entry = {location.state.entry.entry}></Editor>
    // );
    let entry;
    let DEFAULT_INITIAL_DATA;
    const ejInstance = useRef();
    const [series, setSeries] = useState(null);
    // const DEFAULT_INITIAL_DATA = JSON.parse(entry.entryJson);

    const initEditor = () => {
        const editor = new EditorJS({
            holder: 'editorjs',
            onReady: () => {
                ejInstance.current = editor;
            },
            autofocus: true,
            data: DEFAULT_INITIAL_DATA,
            onChange: async () => {
                let content = await editor.saver.save();
                entry.entryJson = JSON.stringify(content);
                EntryApi.updateEntry(entry).then(function (data){});
            },
            tools: {
                header: Header,
                image: {
                    class: ImageTool,
                    config: {
                        endpoints: {
                            byFile: 'http://localhost:8090/api/image/save', // Your backend file uploader endpoint
                            byUrl: '', // Your endpoint that provides uploading by Url
                        },
                        additionalRequestHeaders: {
                            'Authorization': 'Bearer ' + sessionStorage.getItem("jwt")
                        }
                    }
                }
            },
        });
    };

    // This will run only once
    useEffect(() => {
        entry = location.state.entry.entry;
        DEFAULT_INITIAL_DATA = JSON.parse(entry.entryJson);
        const fetchSeries = async () => {
            const rsp = SeriesApi.getSeriesById(entry.seriesId);
            const ser = await rsp;
            setSeries(ser[0]);
        }
        fetchSeries();

        if (ejInstance.current === null) {
            initEditor();
        }

        return () => {
            ejInstance?.current?.destroy();
            ejInstance.current = null;
        };
    }, []);

    const handleSubmit = () => {
        const edjsParser = edjsHTML();
        ejInstance.current.save().then((outputData) => {
            const html = edjsParser.parse(outputData);
            entry.entryHtml = JSON.stringify(html);
            EntryApi.updateEntry(entry).then(function () {});

        }).catch((error) => {
            console.log('Saving failed: ', error)
        });
    };

    return (
        <div class="flex flex-col md:mx-52 shadow-2xl rounded-2xl">
            <div id='editorjs'></div>
            <button onClick={() => handleSubmit()} type="submit"
                    class="m-4 px-2 py-1 rounded-md text-slate-50 bg-orange-700 hover:bg-orange-800">
                Save Entry
            </button>
            <NavLink class="m-4 px-2 py-1 rounded-md text-center text-slate-50 bg-orange-700 hover:bg-orange-800"
                to={{pathname:'/editSeries', state: {series: {series}}}}>
                Return to Entries
            </NavLink>
        </div>
    );
}
export default EditEntryPage;