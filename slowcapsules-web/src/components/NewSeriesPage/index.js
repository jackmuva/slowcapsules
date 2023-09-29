import {Redirect, useLocation} from "react-router-dom";
import {useState} from "react";
import SeriesApi from "../../api/SeriesApi";


function NewSeriesPage() {
    const location = useLocation();
    const [errorMessage, setErrorMessage] = useState(null);
    console.log(location.state.writer.writer[0])
    function getDateTime() {
        let now     = new Date();
        let year    = now.getFullYear();
        let month   = now.getMonth()+1;
        let day     = now.getDate();
        let hour    = now.getHours();
        let minute  = now.getMinutes();
        let second  = now.getSeconds();
        if(month.toString().length === 1) {
            month = '0'+month;
        }
        if(day.toString().length === 1) {
            day = '0'+day;
        }
        if(hour.toString().length === 1) {
            hour = '0'+hour;
        }
        if(minute.toString().length === 1) {
            minute = '0'+minute;
        }
        if(second.toString().length === 1) {
            second = '0'+second;
        }
        let dateTime = year+'-'+month+'-'+day+'T'+hour+':'+minute+':'+second;
        return dateTime;
    }

    const handleSubmit = () => {
       let series = {
            datetime: getDateTime(),
            numEntries: 0,
            title: null,
            summary: null,
            tags: null,
            cadence: null,
            penName: location.state.writer.writer[0].penName,
            email: location.state.writer.writer[0].email,
            published: false
        }

        series.title = document.getElementById("title").value;
        series.summary = document.getElementById("summary").value;
        series.tags = document.getElementById("tags").value;
        series.cadence = document.getElementById("cadence").value;
        if (series.title === '' || series.summary === '' || series.cadence === '') {
            setErrorMessage('Title, Summary, and Cadence may not be blank');
        } else {
            SeriesApi.postSeries(series).then(() => {});
            setErrorMessage('Created Successfully');
        }
    }

    if (errorMessage === 'Created Successfully') {
        return <Redirect to='/writerDashboard'/>
    } else {
        return (
            <div className="form">
                <div className="form-body">
                    <div className="title">
                        <label className="form__label" htmlFor="title"> Title </label>
                        <input type="text" id="title" className="form__input" placeholder="Title"/>
                    </div>
                    <div className="summary">
                        <label className="form__label" htmlFor="summary"> Summary </label>
                        <input className="form__input" type="text" id="summary" placeholder="Summary"/>
                    </div>
                    <div className="tags">
                        <label className="form__label" htmlFor="tags"> Tags </label>
                        <input className="form__input" type="text" id="tags"
                               placeholder="Any keyword you'd want your series to be searchable for separated by a comma"/>
                    </div>
                    <div className="cadence">
                        <label htmlFor="cadence">Cadence</label>
                        <input type="number" id="cadence" min="1" max="30"/>
                    </div>
                </div>
                {errorMessage && <div className="error"> {errorMessage} </div>}
                <div className="footer">
                    <button onClick={() => handleSubmit()} type="submit" className="btn">Create</button>
                </div>
            </div>
        );
    }
};
export default NewSeriesPage;