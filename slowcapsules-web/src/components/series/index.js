import "./series.css";
import SubscribeModal from "../SubscribeModal";
import {NavLink} from "../Navbar/NavbarElements";
import React from "react";

const Series = ({ series, fromWriterDashboard }) => {
    const writerBool = fromWriterDashboard
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
            {writerBool && sessionStorage.getItem("jwt") !== null && <NavLink to={{
                pathname:'/editSeries',
                state: {series: {series},
                    title: "hi"}
            }}>
                Edit
            </NavLink>}
        </div>
    )
}
export default Series;