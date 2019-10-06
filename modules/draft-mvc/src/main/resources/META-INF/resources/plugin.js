window.saveDraft = function() {

    var form = $('form#_com_liferay_journal_web_portlet_JournalPortlet_fm1');

    console.log(form.serialize());

    var titleMap = form.find("input[id^='_com_liferay_journal_web_portlet_JournalPortlet_titleMapAsXML_']");

    var titleMapToString = '{';

    Array.prototype.forEach.call(titleMap, entry => {
        var lang = entry.id.slice(-5);
        var title = entry.value;

        titleMapToString += '"' + lang + '":"' + title + '",';
    });
    titleMapToString = titleMapToString.slice(0, -1);
    titleMapToString += '}';

    var friendlyUrlMap = form.find("input[id^='_com_liferay_journal_web_portlet_JournalPortlet_friendlyURL_']");

    var friendlyUrlMapToString = '{';

    Array.prototype.forEach.call(friendlyUrlMap, entry => {
        var lang = entry.id.slice(-5);
        var url = entry.value;

        friendlyUrlMapToString += '"' + lang + '":"' + url + '",';
    });

    friendlyUrlMapToString = friendlyUrlMapToString.slice(0, -1);
    friendlyUrlMapToString += '}';

    var articleId = form.find('#_com_liferay_journal_web_portlet_JournalPortlet_articleId').attr('value');

    var groupId = form.find('#_com_liferay_journal_web_portlet_JournalPortlet_groupId').attr('value');

    var status = form.find('#_com_liferay_journal_web_portlet_JournalPortlet_workflowAction').attr('value');

    var lang = form.find('#_com_liferay_journal_web_portlet_JournalPortlet_languageId').attr('value');

    var title = form.find('#_com_liferay_journal_web_portlet_JournalPortlet_titleMapAsXML_' + lang).attr('value');

    var description = document.getElementById('_com_liferay_journal_web_portlet_JournalPortlet_descriptionMapAsXMLEditor').innerHTML;

    // var content = document.getElementsByClassName('html-editor')[0].innerHTML;

    var content = document.getElementsByClassName('html-editor');

    var formGroups = document.querySelectorAll('.form-builder-field .form-group');

    ///////// TODO: delete inner groups from NodeList

    for (var k = 0; k < formGroups.length; k++) {

        var subSelectors = formGroups[k].querySelectorAll('.form-group');

        // var formGroupsToArray = Array.from(formGroups);
        // var subSelectorsToArray = Array.from(subSelectors);

        for(var z = 0; z < formGroups.length; z++){
            if(subSelectors.includes(formGroups[z])){
                formGroups[z].remove();
            }
        }
    }

    for (var i = 0; i < formGroups.length; i++){
        if(formGroups[i]){
            var formGroupsChildren = formGroups[i];
            console.log('formGroups[' + i + '] = ', formGroupsChildren);
            for (var j = 0; j < formGroupsChildren.querySelectorAll('*').length; j++){
                console.log('formGroupsChildren[' + j + '] = ', formGroupsChildren.querySelectorAll('*')[j]);
            }
        }
    }

    // for (var i = 0; i < formGroups.length; i++){
    //     var formGroupsChildren = formGroups[i];
    //     console.log('formGroups[' + i + '] = ', formGroupsChildren);
    //     for (var j = 0; j < formGroupsChildren.children.length; j++){
    //         console.log('formGroupsChildren[' + j + '] = ', formGroupsChildren.children[j]);
    //     }
    // }



    // if (content.length === 0){
    //     // TODO: add formBuilder elements here
    // } else {
    //     content = content[0].innerHTML;
    // }
    //
    // Array.prototype.forEach.call(formBuilder, formGroup => {
    //
    //     console.log(formGroup.children);
    // });

    Liferay.Service(
            '/journal.journalarticle/get-latest-article',
            {
                groupId: groupId,
                articleId: articleId,
                status: status
            },
            function (obj) {

                console.log('old obj.content = ', obj.content);

                Liferay.Service(
                        '/journal.journalarticle/update-article-translation',
                        {
                            groupId: obj.groupId,
                            articleId: obj.articleId,
                            version: obj.version,
                            locale: lang,
                            title: title,
                            description: description,
                            content: /*'<?xml version="1.0"?><root available-locales="en_US" default-locale="en_US"><dynamic-element name="content" type="text_area" index-type="text" instance-id="rzqj"><dynamic-content language-id="en_US"><![CDATA[<p>'
                                                 + new Date() + '</p>]]></dynamic-content></dynamic-element></root>'*/obj.content,
                            images: {}
            },
                function(obj) {
                    console.log(obj.content);



                    form.find('#_com_liferay_journal_web_portlet_JournalPortlet_formDate').val(new Date().valueOf());
                }
            );
            }
    );
};

function test(){
    var portletURL = Liferay.PortletURL.createURL(themeDisplay.getURLControlPanel());

    // portletURL.setDoAsGroupId('true');
    // portletURL.setLifecycle(Liferay.PortletURL.ACTION_PHASE);
    // portletURL.setParameter('cmd', 'add_temp');
    portletURL.setParameter('javax.portlet.action', '/document_library/upload_file_entry');
    portletURL.setParameter('p_auth', Liferay.authToken);
    portletURL.setPortletId(Liferay.PortletKeys.DOCUMENT_LIBRARY);

}