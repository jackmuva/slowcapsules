import React, {useState} from 'react';
const SeriesFilter = () => {
    const [searchInput, setSearchInput] = useState("");

    const handleChange = (e) => {
        e.preventDefault();
        setSearchInput(e.target.value);
    };


}

export default SeriesFilter;