import "./series.css";

const Series = ({ series }) => {
    return (
        <div>
            <div className = "row mt-2">
                <h5 className = "col-md-12"> {series.title}</h5>
            </div>
            <div className = "row">
                <h3 className = "col-md-12">{series.pen_name}</h3>
            </div>
            <div className = "col-md-5">
                <p>{series.summary}</p>
                <p> {series.num_entries} </p>
                <p className = "cadence"> {series.cadence} </p>
                <p> {series.tags} </p>
            </div>
        </div>
    )
}
export default Series;