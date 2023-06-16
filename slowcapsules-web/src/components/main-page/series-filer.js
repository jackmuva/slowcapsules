import { FontAwesomeIcon } from "@fortawesome/react-fontawesome"
import { faMagnifyingGlass } from "@fortawesome/free-solid-svg-icons"
import SeriesApi from "../../api/SeriesApi";
const SeriesFilter = ({ posts, setSearchResults }) => {
    const handleSubmit = (e) => e.preventDefault()

    const handleSearchChange = (e) => {
        const filtered = async () => {
            console.log("filter called");
            const rsp = SeriesApi.getSeriesByKeyword(e.target.value);
            const series = await rsp;
            console.log(series);
            return setSearchResults(series);
        }
        if(e.target.value){
            return filtered();
        }
        if (!e.target.value) return setSearchResults(posts)
    }

    return (
        <header>
            <form className="search" onSubmit={handleSubmit}>
                <input
                    className="search__input"
                    type="text"
                    id="search"
                    onChange={handleSearchChange}
                />
                <button className="search__button">
                    <FontAwesomeIcon icon={faMagnifyingGlass} />
                </button>
            </form>
        </header>
    )
}

export default SeriesFilter;