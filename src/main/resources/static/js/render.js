/**
 * Bindings to use mustache.js with Nashorn.
 */

/**
 * String templates: the templates content
 * Map model: the view model
 * String url: the templates url (since 4.2.2)
 */
function render(template, model, url) {

    //print("templates :" +  template);
    //print("model :" +  model);
    //print("url :" +  url);

	return Mustache.render(template, toJsonObject(model));

}

// Create a real JSON object from the model Map 
function toJsonObject(model) {
    var o = {};
    for (var k in model) {

        //Print the object type
        print(Object.prototype.toString.call(model[k]) + ":" + model[k]);

        //Convert Object String to Javascript JSON
        if (k.indexOf("_json") > -1) {
            o[k] = JSON.parse(model[k]);
            continue;
        }

        // Convert Iterable like List to real JSON array
        if (model[k] instanceof Java.type("java.lang.Iterable")) {
            o[k] = Java.from(model[k]);
        }
        else {
            o[k] = model[k];
        }
    }

    print(o);

    return o;
}