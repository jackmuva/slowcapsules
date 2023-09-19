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
                    <NavLink to='/signup' activeStyle>
                        Sign Up
                    </NavLink>
                    <NavLink to='/login' activeStyle>
                        Login
                    </NavLink>
                    <NavLink to='/about' activeStyle>
                        About
                    </NavLink>
                    {sessionStorage.getItem("jwt") != null && <NavLink>
                        Writer Home
                    </NavLink>}
                </NavMenu>
            </Nav>
        </div>
    );
};

export default Navbar;