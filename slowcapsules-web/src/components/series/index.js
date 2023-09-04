import "./series.css";
import SubscribeModal from "../SubscribeModal";

const Series = ({ series }) => {
    return (
        <div className = "seriesCard">
            <div className = "row mt-2">
                <h2 className = "col-md-12"> {series.title}</h2>
            </div>
            <div className = "row">
                <h5 className = "col-md-12"> Written By: {series.penName}</h5>
            </div>
            <div className = "col-md-5">
                <p> Summary: {series.summary}</p>
                <p> Total Entries: {series.numEntries} </p>
                <p> Cadence: Every {series.cadence} days</p>
                <p> Tags: {series.tags} </p>
            </div>
            <SubscribeModal series = {series}></SubscribeModal>
        </div>
    )
}
export default Series;