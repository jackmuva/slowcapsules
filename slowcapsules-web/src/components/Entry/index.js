import {NavLink} from "../Navbar/NavbarElements";
import React, {useEffect, useState} from "react";
import EntryApi from "../../api/EntryApi";
import "./entry.css";

const Entry = ({ entry, maxEntry}) => {
    const [editable, setEditable] = useState(false);
    const [errorMessage, setErrorMessage] = useState(null);

    const toggleEditable = () => {
        setEditable(!editable);
    }

    const handleSubmit = () => {
        let orderNum = document.getElementById("order").value;
        let title = document.getElementById("title").value;

        entry.orderNum = orderNum;
        entry.title = title;

        if(title === '' || orderNum === ''){
            setErrorMessage('Title and Order may not be blank');
        } else {
            EntryApi.postNewEntry(entry).then(() => {});
            toggleEditable();
        }
    }

    if(!editable) {
        return (
            <div className="entryCard">
                <button onClick={() => toggleEditable()} type="submit" className="btn">Change title and order</button>
                <div className="col-md-5">
                    <p> Order Number: {entry.orderNum} </p>
                    <p> Entry Title: {entry.title} </p>
                </div>
                {sessionStorage.getItem("jwt") !== null && <NavLink to={{
                    pathname: '/editEntry',
                    state: {entry: {entry}}
                }}>
                    Edit Content
                </NavLink>}
            </div>
        );
    } else {
        return (<div className="entryCard">
            {errorMessage && <div className="error"> {errorMessage} </div>}
            <button onClick={() => handleSubmit()} type="submit" className="btn">Save</button>
            <div className="form">
            <div className="form-body">
                <div className="order">
                    <label className="form__label" htmlFor="order">Order </label>
                    <input type="number" id="order" min="1" max = {maxEntry} defaultValue={entry.orderNum}/>
                </div>
                <div className="title">
                    <label className="form__label" htmlFor="title">Title </label>
                    <input type="title" id="title" className="form__input" defaultValue={entry.title}/>
                </div>
            </div>
            </div>
            {sessionStorage.getItem("jwt") !== null && <NavLink to={{
                pathname: '/editEntry',
                state: {entry: {entry}}
            }}>
                Edit Content
            </NavLink>}
        </div>);
    }
}
export default Entry;