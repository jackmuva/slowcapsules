import WriterApi from "../../api/WriterApi";
import {useEffect, useState} from "react";
import SeriesApi from "../../api/SeriesApi";
import SeriesFilter from "../main-page/series-filer";
import SeriesPage from "../main-page/series-page";


function WriterDashboard (){
    const [writer, setWriter] = useState([]);
    const [series, setSeries] = useState([]);
    const [searchResults, setSearchResults] = useState([]);

    useEffect(() => {
        const fetchWriter = async() => {
            const rsp = WriterApi.getLoggedInWriter();
            const wr = await rsp;
            setWriter(wr);
        }
        const fetchSeries = async(name) => {
            const rsp = SeriesApi.getSeriesByWriter(name);
            const wrSeries = await rsp;
            setSeries(wrSeries);
        }
        fetchWriter();
        console.log(writer);
        fetchSeries(writer.penName);
    }, []);

    if(series.length === 0){
        return(
            <div>
                Create your first post
            </div>
        );
    }else{
        return(
            <div>
                <SeriesFilter posts={series} setSearchResults={setSearchResults}/>
                <SeriesPage allSeries = {searchResults}></SeriesPage>
            </div>
        );
    }
}
export default WriterDashboard;