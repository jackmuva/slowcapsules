import './App.css';
import Header from "./components/Header/header";
import { useEffect, useState } from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import SeriesPage from "./components/main-page/series-page";
import SeriesApi from "./api/SeriesApi";
import Navbar from "./components/Navbar";
import SeriesFilter from "./components/main-page/series-filer";
import SignUpPage from "./components/SignUpPage";
import LoginPage from "./components/LoginPage/LoginPage";
import WriterDashboard from "./components/WriterDashboard";

function App() {
    const [allSeries, setAllSeries] = useState([]);
    const [searchResults, setSearchResults] = useState([]);
    const [user, setUser] = useState([]);

    useEffect(() => {
        const fetchSeries = async () => {
            const rsp = SeriesApi.getNewestSeries();
            const series = await rsp;
            setAllSeries(series);
            setSearchResults(series);
        };
        fetchSeries();
    }, []);

  return (
      <Router>
        <div className="container">
          <Header subtitle = "Write and Subscribe to Email Series"/>
            <Navbar/>
            <Switch>
                <Route path = "/signup">
                    <SignUpPage></SignUpPage>
                </Route>
                <Route path = "/login">
                    <LoginPage setWriter = {setUser}></LoginPage>
                </Route>
                <Route path = "/writerDashboard">
                    <WriterDashboard></WriterDashboard>
                </Route>
                <Route path = "/">
                    <SeriesFilter posts={allSeries} setSearchResults={setSearchResults}/>
                    <SeriesPage allSeries = {searchResults}></SeriesPage>
                </Route>
            </Switch>
        </div>
      </Router>
  );
}

export default App;
