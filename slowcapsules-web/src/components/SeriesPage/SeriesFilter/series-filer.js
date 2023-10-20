import { FontAwesomeIcon } from "@fortawesome/react-fontawesome"
import { faMagnifyingGlass } from "@fortawesome/free-solid-svg-icons"
import SeriesApi from "../../../api/SeriesApi";
const SeriesFilter = ({ posts, setSearchResults }) => {
    const handleSubmit = (e) => e.preventDefault()

    const handleSearchChange = (e) => {
        const filtered = async () => {
            const rsp = SeriesApi.getSeriesByKeyword(e.target.value);
            const series = await rsp;
            return setSearchResults(series);
        }
        if(e.target.value){
            return filtered();
        }
        if (!e.target.value) return setSearchResults(posts)
    }

    return (
        <div className="mb-1">
            <div className="inline-flex relative mb-0 flex w-1/4 flex-wrap items-stretch">
                <input
                    type="search"
                    className="relative m-4 block flex-auto rounded border border-solid border-neutral-300 bg-transparent bg-clip-padding px-3 py-[0.25rem] text-base font-normal leading-[1.6] text-neutral-700 outline-none transition duration-200 ease-in-out focus:z-[3] focus:border-primary focus:text-neutral-700 focus:shadow-[inset_0_0_0_1px_rgb(59,113,202)] focus:outline-none dark:border-neutral-600 dark:text-neutral-200 dark:placeholder:text-neutral-200 dark:focus:border-primary"
                    placeholder="Search"
                    aria-label="Search"
                    id="search"
                    onChange={handleSearchChange}
                />
                <button onSubmit={handleSubmit}>
                    <FontAwesomeIcon icon={faMagnifyingGlass} />
                </button>
            </div>
        </div>
    )
}

export default SeriesFilter;