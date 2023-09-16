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
        fetchWriter();
    }, []);

    useEffect(() => {
        const fetchSeries = async(name) => {
            const rsp = SeriesApi.getSeriesByWriter(name);
            const wrSeries = await rsp;
            setSeries(wrSeries);
            setSearchResults(wrSeries);
        }
        if(writer.length !== 0){
            fetchSeries(writer[0].penName)
        }
    }, [writer])

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
                <SeriesPage allSeries = {searchResults} fromWriter = {true}></SeriesPage>
            </div>
        );
    }
}
export default WriterDashboard;