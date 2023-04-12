import logo from './logo.svg';
const Header = () =>(
    <header className = "row">
        <div className="col-md-5">
            <img src={logo} className="logo" alt="logo"/>
        </div>
        <div className = "col-md-7 mt-5 subtitle">
            Write and Subscribe to Email Series
        </div>
    </header>
);

export default Header;