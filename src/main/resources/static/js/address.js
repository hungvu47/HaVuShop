const apiUrl = "https://vietnam-administrative-division-json-server-swart.vercel.app";
const apiEndpointDistrict = apiUrl + '/district/?idProvince=';
const apiEndpointCommune = apiUrl + '/commune/?idDistrict=';

async function getDistrict(idProvince) {
    const { data : districtList } = await axios.get(apiEndpointDistrict + idProvince);
    return districtList
}

async function getCommune(idDistrict){
    const { data : communeList } = await axios.get(apiEndpointCommune + idDistrict);
    return communeList
}

document.querySelector('#city-province').addEventListener("change", async () => {
    document.querySelector('.district-town-select > span').style.display = "block";
    const selectedProvinceOption = document.querySelector('#city-province').options[document.querySelector('#city-province').selectedIndex];

    const selectedProvince = selectedProvinceOption.value;
    const selectedProvinceId = selectedProvinceOption.getAttribute('data-id');

    document.querySelector('#ward-commune').innerHTML = "<option value='0'>&nbspChọn Phường/Xã...</option>";

    try {
        const { data: districtList } = await axios.get(apiEndpointDistrict + selectedProvinceId);
        let outputDistrict = "<option value='0'>&nbspChọn Quận/Huyện...</option>";

        for (const district of districtList) {
            if (district.idProvince === selectedProvinceId) {
                outputDistrict += `<option value='${district.name}' data-id='${district.idDistrict}'>&nbsp${district.name}</option>`;
            }
        }

        document.querySelector('#district-town').innerHTML = outputDistrict;
    } catch (error) {
        console.error("Error fetching districts:", error);
    } finally {
        document.querySelector('.district-town-select > span').style.display = "none";
    }
});




document.querySelector('#district-town').addEventListener("change", async () => {
    document.querySelector('.ward-commune-select > span').style.display = "block";
    const selectedDistrictOption = document.querySelector('#district-town').options[document.querySelector('#district-town').selectedIndex];

    const selectedDistrict = selectedDistrictOption.value;
    const selectedDistrictId = selectedDistrictOption.getAttribute('data-id');

    document.querySelector('#ward-commune').innerHTML = "<option value='0'>&nbspChọn Phường/Xã...</option>";

    try {
        const { data: communeList } = await axios.get(apiEndpointCommune + selectedDistrictId);
        let outputCommune = "<option value='0'>&nbspChọn Phường/Xã...</option>";

        for (const commune of communeList) {
            if (commune.idDistrict === selectedDistrictId) {
                outputCommune += `<option value='${commune.name}' data-id='${commune.idCommune}'>&nbsp${commune.name}</option>`;
            }
        }

        document.querySelector('#ward-commune').innerHTML = outputCommune;
    } catch (error) {
        console.error("Error fetching communes:", error);
    } finally {
        document.querySelector('.ward-commune-select > span').style.display = "none";
    }
});

