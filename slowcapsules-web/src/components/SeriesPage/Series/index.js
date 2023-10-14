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
        <div class="grid grid-cols-4 border-b-2 border-stone-200">
            <div class="p-1 m-0 col-span-3">
                <div class = "flex-auto">
                    <h2 class="mb-0 font-serif text-3xl font-bold"> {series.title}</h2>
                    <h3 class="ml-3 my-0 font-serif text-base"> Written By: {series.penName}</h3>
                    <h3 class="font-serif text-base overflow-auto">Summary: {series.summary}</h3>
                </div>
                <div class="flex">
                    <p class="mr-3 font-serif text-xs"> Total Entries: <strong>{series.numEntries}</strong> </p>
                    <p class="mr-3 font-serif text-xs"> Cadence: <strong>Every {series.cadence} days</strong></p>
                    {series.tags !== '' &&
                        <p class="mr-3 font-serif text-xs"> Tags: {series.tags} </p>}
                </div>
            </div>
            <div class="col-span-1">
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