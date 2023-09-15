import {useLocation} from "react-router-dom";
import Editor from "../Editor";

function EditEntryPage(){
    const location = useLocation();
    console.log(location)
    return(
        <div>
            <div> { location.state.entry.entry.entryText } </div>
            <Editor></Editor>
        </div>
    );
}
export default EditEntryPage;