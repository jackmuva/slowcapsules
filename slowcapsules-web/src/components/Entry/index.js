import {NavLink} from "../Navbar/NavbarElements";
import React from "react";


const Entry = ({ entry }) => {
    return (
        <div className = "entryCard">
            <div className = "col-md-5">
                <p> Order Number: {entry.orderNum} </p>
                <p> Entry Title: {entry.title} </p>
            </div>
            {sessionStorage.getItem("jwt") !== null && <NavLink to={{
                pathname:'/editEntry',
                state: {entry: {entry}}
            }}>
                Edit
            </NavLink>}
        </div>
    );
}
export default Entry