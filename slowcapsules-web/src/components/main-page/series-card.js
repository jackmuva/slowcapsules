import Series from "../series";

const SeriesCard = ({ series }) => {
    if(series)
        return(
            <div>
                <div className = "row seriesCard">

                </div>
                <Series series = {series} />
            </div>
        );
    return <div> No series at this time </div>;
}

export default SeriesCard;