import EditorJS from "@editorjs/editorjs";
import React, {useEffect, useRef} from "react";
import Header from '@editorjs/header';
import ImageTool from '@editorjs/image';
import edjsHTML from 'editorjs-html';

const Editor = () => {
    const ejInstance = useRef();

    const DEFAULT_INITIAL_DATA =  {
        // "time": new Date().getTime(),
        "blocks": [
            {
                "type": "header",
                "data": {
                    "text": "This is my awesome editor!",
                    "level": 1
                }
            },
        ]
    }

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

                console.log(content);
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
            console.log('Article data: ', outputData)
            console.log('html: ', html);
        }).catch((error) => {
            console.log('Saving failed: ', error)
        });
    };

    console.log(ejInstance);

    return (
        <>
            <div id='editorjs'></div>
            <button onClick={() => handleSubmit()} type="submit" className="btn">Save Entry</button>
        </>
    );
};
export default Editor;