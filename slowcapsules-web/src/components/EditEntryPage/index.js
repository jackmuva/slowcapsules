import {useLocation} from "react-router-dom";
import Editor from "../Editor";

function EditEntryPage(){
    const location = useLocation();
    return(
        <div>
            <Editor entry = {location.state.entry.entry}></Editor>
        </div>
    );
}
export default EditEntryPage;