const Header = () =>(
    <div className="mx-auto">
        <header className="flex justify-between items-center sticky top-0 z-10 py-4 bg-blue-900">
            <div className="flex-shrink-0 ml-6 cursor-pointer">
                <i className="fas fa-wind fa-2x text-yellow-500"></i>
                <span className="text-3xl font-semibold text-blue-200">Turtle Capsule</span>
            </div>
            <ul className="flex overflow-x-hidden mr-10 font-semibold">
                <li className="mr-6 p-1">
                    <a className="text-white hover:text-blue-300" href="/">Home</a>
                </li>
                {sessionStorage.getItem("jwt") == null && <li className="mr-6 p-1">
                    <a className="text-white hover:text-blue-300" href="/signup">Sign Up</a>
                </li>}
                {sessionStorage.getItem("jwt") == null && <li className="mr-6 p-1">
                    <a className="text-white hover:text-blue-300" href="/Login">Login</a>
                </li>}
                <li className="mr-6 p-1">
                    <a className="text-white hover:text-blue-300" href="/about">About</a>
                </li>
                {sessionStorage.getItem("jwt") != null && <li className="mr-6 p-1">
                    <a className="text-white hover:text-blue-300" href="/writerDashboard">Writer Home</a>
                </li>}
            </ul>
        </header>
    </div>
);

export default Header;