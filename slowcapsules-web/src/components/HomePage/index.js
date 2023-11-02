import {useEffect, useState} from "react";
import SeriesApi from "../../api/SeriesApi";
import SeriesFilter from "../SeriesPage/SeriesFilter/series-filer";
import SeriesPage from "../SeriesPage/series-page";


function HomePage(){
    const [allSeries, setAllSeries] = useState([]);
    const [searchResults, setSearchResults] = useState([]);

    useEffect(() => {
        const fetchSeries = async () => {
            const rsp = SeriesApi.getNewestSeries();
            const series = await rsp;
            setAllSeries(series);
            setSearchResults(series);
        };
        fetchSeries();
    }, []);

    return (
        <div>
            <SeriesFilter posts={allSeries} setSearchResults={setSearchResults}/>
            <SeriesPage allSeries = {searchResults}></SeriesPage>
        </div>
    )
};
export default HomePage;