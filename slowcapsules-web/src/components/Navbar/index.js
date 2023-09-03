import React from 'react';
import {Nav, NavLink, Bars, NavMenu, NavBtn, NavBtnLink,} from './NavbarElements';

const Navbar = () => {
    return (
        <div>
            <Nav>
                <Bars />
                <NavMenu>
                    <NavLink to='/about' activeStyle>
                        About
                    </NavLink>
                    <NavLink to='/signup' activeStyle>
                        Sign Up
                    </NavLink>
                </NavMenu>
                <NavBtn>
                    <NavBtnLink to='/login'>Login</NavBtnLink>
                </NavBtn>
            </Nav>
        </div>
    );
};

export default Navbar;