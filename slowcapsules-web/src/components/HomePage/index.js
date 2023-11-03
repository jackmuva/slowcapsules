import {useEffect, useState} from "react";
import SeriesApi from "../../api/SeriesApi";
import SeriesFilter from "../SeriesPage/SeriesFilter/series-filer";
import SeriesPage from "../SeriesPage/series-page";
import PaginationBar from "../PaginationBar";


function HomePage(){
    const [allSeries, setAllSeries] = useState([]);
    const [searchResults, setSearchResults] = useState([]);
    const [pageNum, setPageNum] = useState(0);

    useEffect(() => {
        const fetchSeries = async () => {
            const rsp = SeriesApi.getNewestSeries(pageNum);
            const series = await rsp;
            setAllSeries(series);
            setSearchResults(series);
        };
        fetchSeries();
    }, [pageNum]);

    return (
        <div>
            <SeriesFilter posts={allSeries} setSearchResults={setSearchResults}/>
            <SeriesPage allSeries = {searchResults}></SeriesPage>
            <div className="flex flex-col w-screen items-center text-center">
                <PaginationBar page = {pageNum} setPage = {setPageNum}></PaginationBar>
            </div>
        </div>
    )
};
export default HomePage;