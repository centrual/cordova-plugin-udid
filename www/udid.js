var exec = require('cordova/exec'),
    cordova = require('cordova');


function Udid() {
	this.udid = null;
	exec( (data) => { this.udid = data.udid; }, (e) => {}, "Udid", "get", []);
}

Udid.prototype.getUdid = function() {
	return this.udid;
};

module.exports = new Udid();

// for utf8 => ğüşıöçĞÜŞİÖÇ