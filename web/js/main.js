/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


document.addEventListener('DOMContentLoaded', function () {
    var side = document.querySelectorAll('.sidenav');
    var sideNav = M.Sidenav.init(side, {});
    var tabs_1 = document.querySelector('.tabs');
    var instance = M.Tabs.init(tabs_1, {});
    var tabs_2 = document.querySelector('.tab-checkIn');
    var instance = M.Tabs.init(tabs_2, {});
    var tabs_3 = document.querySelector('.tab-hotels');
    var instance = M.Tabs.init(tabs_3, {});


    var date = new Date();

    var select = document.querySelectorAll('select');
    var instancesSelect = M.FormSelect.init(select, {});
    var picker = document.querySelectorAll('.datepicker');
    var instancesPick = M.Datepicker.init(picker, {
        "defaultTime": (new Date(date.setDate(date.getDate() - (365 * 18)))),
        format: "yyyy-mm-dd"
    });
});

$(document).ready(function () {
    $('.carousel').carousel({
        dist: -250,
        indicators: true
    });
});