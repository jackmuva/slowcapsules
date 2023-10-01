import "./series.css";
import SubscribeModal from "../../SubscribeModal";
import {NavLink} from "../../Navbar/NavbarElements";
import React, {useState} from "react";
import SeriesApi from "../../../api/SeriesApi";

const Series = ({ series, fromWriterDashboard }) => {
    const [published, setPublished] = useState(null);
    const writerBool = fromWriterDashboard

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
    };

    const handleChange = (e) => {
        // console.log(e.target.checked);
        console.log(!e.target.checked);
        series.datetime = getDateTime();
        series.published = !e.target.checked
        // SeriesApi.postSeries(series).then(() => {});
        setPublished(series.published);
    };

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
            {!writerBool && <SubscribeModal series = {series}></SubscribeModal>}
            {writerBool && sessionStorage.getItem("jwt") !== null && <NavLink to={{
                pathname:'/editSeries',
                state: {series: {series}}
            }}>
                Edit
            </NavLink>}
            {
                writerBool &&
                <>
                    <div>Publish</div>
                    <label className="switch">
                        <input type="checkbox" onChange={(e) => handleChange(e)} checked = {series.published}/>
                        <span className="slider round"></span>
                    </label>
                </>
            }
        </div>
    );
};
export default Series;