import {useLocation} from "react-router-dom";

function EditEntryPage(){
    const location = useLocation();
    console.log(location)
    return(
        <div> { location.state.entry.entry.entryText } </div>
    );
}
export default EditEntryPage;