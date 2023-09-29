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


    if(entries.length === 0){
        return (
            <div>
                <NavLink to={{
                    pathname:'/newEntry',
                    state: {series: {series}}}}>
                    New Entry
                </NavLink>
                <div>No entries yet</div>
            </div>);
    } else{
        const entryItems = entries.sort(function(a, b){return a.orderNum - b.orderNum}).map(entry => {
            return (
                <div>
                    <NavLink to={{
                        pathname:'/newEntry',
                        state: {series: {series}}}}>
                        New Entry
                    </NavLink>
                    <Entry entry = {entry}></Entry>
                </div>);
        }
        );
        return(
            <div> { entryItems }</div>
        );
    }
}
export default EditSeriesPage;