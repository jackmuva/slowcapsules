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
    let entry;
    let DEFAULT_INITIAL_DATA;
    const ejInstance = useRef();
    const [series, setSeries] = useState(null);
    let [globalEntry, setGlobalEntry] = useState(null);

    const initEditor = () => {
        const editor = new EditorJS({
            holder: 'editorjs',
            onReady: () => {
                ejInstance.current = editor;
            },
            // autofocus: true,
            data: DEFAULT_INITIAL_DATA,
            onChange: async () => {
                let content = await editor.saver.save();
                entry.entryJson = JSON.stringify(content);
                EntryApi.updateEntry(entry).then(function (data){});

                const edjsParser = edjsHTML();
                ejInstance.current.save().then((outputData) => {
                    const html = edjsParser.parse(outputData);
                    entry.entryHtml = JSON.stringify(html);
                    EntryApi.updateEntry(entry).then(function () {});
                }).catch((error) => {
                    console.log('Saving failed: ', error)
                });
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
        setGlobalEntry(entry);
    }, []);

    // const handleSubmit = () => {
    //     const edjsParser = edjsHTML();
    //     ejInstance.current.save().then((outputData) => {
    //         const html = edjsParser.parse(outputData);
    //         console.log(entry);
    //         globalEntry.entryHtml = JSON.stringify(html);
    //         EntryApi.updateEntry(globalEntry).then(function () {});
    //     }).catch((error) => {
    //         console.log('Saving failed: ', error)
    //     });
    // };

    return (
        <div>
            <div className="flex sticky top-2 flex-col">
                {/*<button onClick={() => handleSubmit()} type="submit"*/}
                {/*        className="m-2 px-2 py-1 rounded-md text-slate-50 bg-orange-700 hover:bg-orange-800 max-w-fit">*/}
                {/*    Save Entry*/}
                {/*</button>*/}
                <NavLink class="m-2 px-2 py-1 rounded-md text-center text-slate-50 bg-orange-700 hover:bg-orange-800 max-w-fit"
                         to={{pathname: '/editSeries', state: {series: {series}}}}>
                    Return to Entries
                </NavLink>
            </div>
            <div className="flex flex-col items-center">
                <div class="md:w-1/2 shadow-2xl rounded-2xl" id='editorjs'></div>
            </div>
        </div>
    );
}
export default EditEntryPage;