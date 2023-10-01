import {useLocation} from "react-router-dom";
import React, {useEffect, useState} from "react";
import EntryApi from "../../api/EntryApi";
import Entry from "../Entry";
import {NavLink} from "../Navbar/NavbarElements";

function EditSeriesPage(){
    const [entries, setEntries] = useState([]);
    const [series, setSeries] = useState(null);
    const location = useLocation();

    useEffect(() =>{
        const fetchEntries = async() => {
            const rsp = EntryApi.getEntriesBySeriesId(location.state.series.series.seriesId);
            const entryRes = await rsp;
            setEntries(entryRes);
        }
        fetchEntries();
        setSeries(location.state.series.series);
    }, []);

    const createEntry = () => {
        let maxOrder = 1;
        if(entries.length > 0){
            maxOrder = entries.sort(function(a, b){return a.orderNum - b.orderNum})[entries.length - 1].orderNum + 1;
        }

        let entry = {
            seriesId: location.state.series.series.seriesId,
            entryJson: "",
            entryHtml: "",
            orderNum: maxOrder,
            title: "New Entry",
            email: location.state.series.series.email
        }
        EntryApi.postNewEntry(entry).then(function () {});
        setEntries([...entries, entry]);
    };

    let maxOrder = 1;
    if(entries.length > 0){
        maxOrder = entries.sort(function(a, b){return a.orderNum - b.orderNum})[entries.length - 1].orderNum;
    }
    if(entries.length === 0){
        return (
            <div>
                <button onClick={() => createEntry()} type="submit" className="btn">Create New Entry</button>
                <div>No entries yet</div>
            </div>);
    } else{
        const entryItems = entries.sort(function(a, b){return a.orderNum - b.orderNum}).map(entry => {
            return (
                <div>
                    <Entry entry = {entry} maxEntry = {maxOrder}></Entry>
                </div>);
        }
        );
        return(
            <div>
                <button onClick={() => createEntry()} type="submit" className="btn">Create New Entry</button>
                { entryItems }
            </div>
        );
    }
}
export default EditSeriesPage;