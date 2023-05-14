import './header.css';

const Header = ({ subtitle }) =>(
    <header className = "Title">
        <h1> Tide Library </h1>
        <br></br>
        <div className = "col-md-7 mt-5 subtitle">
            {subtitle}
        </div>
    </header>
);

export default Header;