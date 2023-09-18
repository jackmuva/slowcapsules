import { useState } from "react";
import AuthorizationApi from "../../api/AuthorizationApi";
import WriterApi from "../../api/WriterApi";
import { Redirect } from 'react-router-dom';

function SignUpPage () {
    const [username, setUsername] = useState(null);
    const [email, setEmail] = useState(null);
    const [password,setPassword] = useState(null);
    const [confirmPassword,setConfirmPassword] = useState(null);
    const [errorMessage, setErrorMessage] = useState(null);

    const handleInputChange = (e) => {
        const {id , value} = e.target;
        if(id === "username"){
            setUsername(value);
        }
        if(id === "email"){
            setEmail(value);
        }
        if(id === "password"){
            setPassword(value);
        }
        if(id === "confirmPassword"){
            setConfirmPassword(value);
        }
    }

    const handleSubmit = () => {
        if(password !== confirmPassword){
            setErrorMessage("Password does not match")
        }
        else{
            let user = {
                username: username,
                email: email,
                password: password
            }
            AuthorizationApi.postNewUser(user);

            let writer = {
                penName: username,
                email: email
            }
            WriterApi.postNewWriter(writer).then(function(data) {
                if(data.writerId){
                    setErrorMessage("Registration Complete");
                }
            });
            setErrorMessage("Error Registering");
        }
    };

    if(errorMessage === "Registration Complete"){
        return <Redirect to='/login'  />
    }
    else{
        return(
            <div className="form">
                <div className="form-body">
                    <div className="username">
                        <label className="form__label" htmlFor="username">Username </label>
                        <input className="form__input" type="text" id="username" placeholder="Username" onChange = {(e) => handleInputChange(e)}/>
                    </div>
                    <div className="email">
                        <label className="form__label" htmlFor="email">Email </label>
                        <input type="email" id="email" className="form__input" placeholder="Email" onChange = {(e) => handleInputChange(e)}/>
                    </div>
                    <div className="password">
                        <label className="form__label" htmlFor="password">Password </label>
                        <input className="form__input" type="password" id="password" placeholder="Password" onChange = {(e) => handleInputChange(e)}/>
                    </div>
                    <div className="confirm-password">
                        <label className="form__label" htmlFor="confirmPassword">Confirm Password </label>
                        <input className="form__input" type="password" id="confirmPassword" placeholder="Confirm Password" onChange = {(e) => handleInputChange(e)}/>
                    </div>
                </div>
                {errorMessage && <div className="error"> {errorMessage} </div>}
                <div className="footer">
                    <button onClick={() => handleSubmit()} type="submit" className="btn">Register</button>
                </div>
            </div>);
    }
};

export default SignUpPage;