import EditorJS from "@editorjs/editorjs";
import React, {useEffect, useRef, useState} from "react";
import Header from '@editorjs/header';
import ImageTool from '@editorjs/image';
import edjsHTML from 'editorjs-html';
import EntryApi from "../../../api/EntryApi";
import SeriesApi from "../../../api/SeriesApi";
import {NavLink} from 'react-router-dom';

const Editor = ({entry}) => {
    const ejInstance = useRef();
    const [series, setSeries] = useState(null);
    const DEFAULT_INITIAL_DATA = JSON.parse(entry.entryJson);

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
        <>
            <div id='editorjs'></div>
            <button onClick={() => handleSubmit()} type="submit" className="btn">Save Entry</button>
            <NavLink to={{
                pathname:'/editSeries',
                state: {series: {series}}
            }}>
                Return to Entries
            </NavLink>
        </>
    );

};
export default Editor;