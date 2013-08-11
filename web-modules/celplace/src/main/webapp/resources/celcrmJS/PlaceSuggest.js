/**
 * Celements CRM Place Suggest
 * This is the Celements CRM auto complete for place input fields like zip, city.
 */
if(typeof CELEMENTS=="undefined"){var CELEMENTS={};};
if(typeof CELEMENTS.CRM=="undefined"){CELEMENTS.CRM={};};
if(typeof CELEMENTS.CRM.place=="undefined"){CELEMENTS.CRM.place={};};

(function(window, undefined) {

//////////////////////////////////////////////////////////////////////////////
// Celements CRM place suggest
//////////////////////////////////////////////////////////////////////////////
CELEMENTS.CRM.place.Suggest = function(cssSelector) {
  // constructor
  this._init(cssSelector);
};

(function() {
  CELEMENTS.CRM.place.Suggest.prototype = {
      _placeFields : undefined,

      _init : function(cssSelector) {
        var _me = this;
        _me._placeFields = $$(cssSelector);
      }

  };
})();

})(window);
