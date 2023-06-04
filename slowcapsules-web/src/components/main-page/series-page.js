import Series from "../series";

const SeriesPage = ({ allSeries }) => {
    const arraySeriesItems = allSeries.map(series =>
        <Series series = {series} />
    );

    if(allSeries)
        return(
            <div>
                {arraySeriesItems}
            </div>
        );
    return <div> No series at this time </div>;
}

export default SeriesPage;