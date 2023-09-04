import Series from "../series";

const SeriesPage = ({ allSeries }) => {
    if(allSeries.length === 0) {
        return <div> No series at this time </div>;
    }
    else {
        const arraySeriesItems = allSeries.map(series =>
            <Series series={series}/>
        );
        return (
            <div>
                {arraySeriesItems}
            </div>
        );
    }

}

export default SeriesPage;