import Series from "./Series";

const SeriesPage = ({ allSeries, fromWriter }) => {
    if(allSeries.length === 0) {
        return <div> No series at this time </div>;
    }
    else {
        const arraySeriesItems = allSeries.map(series =>
            <Series series={series} fromWriterDashboard={fromWriter}/>
        );
        return (
            <div class = "flex items-center bg-yellow-50">
                <div class = "flex flex-col my-3 space-y-0 mx-6 min-w-full">
                    {arraySeriesItems}
                </div>
            </div>
        );
    }

}

export default SeriesPage;