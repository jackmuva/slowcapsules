import React from 'react';
import {Nav, NavLink, Bars, NavMenu} from './NavbarElements';

const Navbar = () => {
    return (
        <div>
            <Nav>
                <Bars />
                <NavMenu>
                    <NavLink to='/' activeStyle>
                        Home
                    </NavLink>
                    {sessionStorage.getItem("jwt") == null && <NavLink to='/signup' activeStyle>
                        Sign Up
                    </NavLink>}
                    {sessionStorage.getItem("jwt") == null && <NavLink to='/login' activeStyle>
                        Login
                    </NavLink>}
                    <NavLink to='/about' activeStyle>
                        About
                    </NavLink>
                    {sessionStorage.getItem("jwt") != null && <NavLink to = "/writerDashboard">
                        Writer Home
                    </NavLink>}
                </NavMenu>
            </Nav>
        </div>
    );
};

export default Navbar;