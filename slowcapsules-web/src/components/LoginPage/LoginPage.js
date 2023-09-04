import { useState } from "react";
import AuthorizationApi from "../../api/AuthorizationApi";

function LoginPage () {
    const [email, setEmail] = useState(null);
    const [password,setPassword] = useState(null);

    const handleInputChange = (e) => {
        const {id , value} = e.target;
        if(id === "email"){
            setEmail(value);
        }
        if(id === "password"){
            setPassword(value);
        }
    }

    const handleSubmit = () => {
        let user = {
            usernameOrEmail: email,
            password: password
        }
        const rsp = AuthorizationApi.postLogin(user).then(function(data) {
            sessionStorage.setItem("jwt", data.accessToken)
        });

    };

    return(
        <div className="form">
            <div className="form-body">
                <div className="email">
                    <label className="form__label" htmlFor="email">Email </label>
                    <input type="email" id="email" className="form__input" placeholder="Email" onChange = {(e) => handleInputChange(e)}/>
                </div>
                <div className="password">
                    <label className="form__label" htmlFor="password">Password </label>
                    <input className="form__input" type="password" id="password" placeholder="Password" onChange = {(e) => handleInputChange(e)}/>
                </div>
            </div>
            <div className="footer">
                <button onClick={() => handleSubmit()} type="submit" className="btn">Login</button>
            </div>
        </div>
    );
};

export default LoginPage;