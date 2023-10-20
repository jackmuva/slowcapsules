import './App.css';
import Header from "./components/Header/header";
import { useEffect, useState } from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import SeriesPage from "./components/SeriesPage/series-page";
import SeriesApi from "./api/SeriesApi";
import SeriesFilter from "./components/SeriesPage/SeriesFilter/series-filer";
import SignUpPage from "./components/SignUpPage";
import LoginPage from "./components/LoginPage/LoginPage";
import WriterDashboard from "./components/WriterDashboard";
import EditSeriesPage from "./components/EditSeriesPage";
import EditEntryPage from "./components/EditEntryPage";
import NewSeriesPage from "./components/NewSeriesPage";

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
        <div class="max-w-full bg-zinc-50">
          <Header/>
            <Switch>
                <Route path = "/newSeries">
                    <NewSeriesPage></NewSeriesPage>
                </Route>
                <Route path = "/editEntry">
                    <EditEntryPage></EditEntryPage>
                </Route>
                <Route path = "/editSeries">
                    <EditSeriesPage></EditSeriesPage>
                </Route>
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
