import {useLocation} from "react-router-dom";


function NewSeriesPage() {
    const location = useLocation();
    const series = {
        datetime: null,
        numEntries: null,
        title: null,
        summary: null,
        tags: null,
        cadence: null,
        penName: location.state.writer.writer.penName,
        email: location.state.writer.writer.email,
        published: null
    }

    console.log(location);

    return(
        <div>hi</div>
    );
}
export default NewSeriesPage;