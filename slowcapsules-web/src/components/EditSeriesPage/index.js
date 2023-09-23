import {useLocation} from "react-router-dom";
import {useEffect, useState} from "react";
import EntryApi from "../../api/EntryApi";
import Entry from "../Entry";

function EditSeriesPage(){
    const [entries, setEntries] = useState([]);
    const location = useLocation();

    useEffect(() =>{
        const fetchEntries = async() => {
            const rsp = EntryApi.getEntriesBySeriesId(location.state.series.series.seriesId);
            const entryRes = await rsp;
            setEntries(entryRes);
        }
        fetchEntries();
    }, []);

    console.log(location)
    console.log();

    if(entries.length === 0){
        return <div> No entries yet </div>
    } else{
        const entryItems = entries.sort(function(a, b){return a.orderNum - b.orderNum}).map(entry => {
            return <Entry entry = {entry}></Entry>
        }
        );
        return(
            <div> { entryItems }</div>
        );
    }
}
export default EditSeriesPage;