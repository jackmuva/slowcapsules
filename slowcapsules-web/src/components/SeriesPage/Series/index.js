import "./series.css";
import SubscribeModal from "../../SubscribeModal";
import {NavLink} from 'react-router-dom';
import React, {useState} from "react";
import SeriesApi from "../../../api/SeriesApi";

const Series = ({ series, fromWriterDashboard }) => {
    // const [published, setPublished] = useState(null);
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
        series.datetime = getDateTime();
        series.published = e.target.checked;
        SeriesApi.postSeries(series).then(() => {});
        // setPublished(series.published);
    };

    return (
        <div class="box grid grid-cols-5 bg-amber-50 border p-6 rounded-lg overflow-hidden">
            <div class="box col-span-4 p-6">
                <div>
                    <h2> {series.title}</h2>
                </div>
                <div >
                    <h5> Written By: {series.penName}</h5>
                </div>
                <div>
                    <p> Summary: {series.summary}</p>
                    <p> Total Entries: {series.numEntries} </p>
                    <p> Cadence: Every {series.cadence} days</p>
                    <p> Tags: {series.tags} </p>
                </div>
            </div>
            <div class = "box">
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
                            <input type="checkbox" onChange={(e) => handleChange(e)} defaultChecked = {series.published}/>
                            <span className="slider round"></span>
                        </label>
                    </>
                }
            </div>
        </div>
    );
};
export default Series;