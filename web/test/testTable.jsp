<!doctype html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>测试表格</title>
    <link type="text/css" href="../page/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="../reference/table/css/normalize.css"/>
    <link rel="stylesheet" type="text/css" href="../reference/table/css/default.css">
    <link type="text/css" href="../reference/table/css/styles.css" media="all" rel="stylesheet"/>
    <link type="text/css" href="../reference/table/css/stickysort.css" media="all" rel="stylesheet"/>
</head>
<body>

<%--<jsp:include page="../page/navBar.jsp"/>--%>

<style>
    .panel .panel-body {
        padding: 0;
        margin: 0;
    }
    #basic-sort {
        position: relative;
        margin-bottom: 0;
    }
</style>

<div class="container-fluid">
    <div class="row">
        <div class="container-fluid">
            <div class="col-xs-6 col-md-10">
                <div class="panel">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <span>涨幅榜</span>
                        </h4>
                    </div>
                    <div class="panel-body">
                        <div class="table-responsive">
                            <div id="basic-sort">
                                <table class="table">
                                    <thead>
                                    <tr>
                                        <th>First Name</th>
                                        <th>Last Name</th>
                                        <th>Email</th>
                                        <th>MD5 hash</th>
                                        <th>SHA1 hash</th>
                                    </tr>
                                    </thead>
                                    <tfoot>
                                    <tr>
                                        <th>First Name</th>
                                        <th>Last Name</th>
                                        <th>Email</th>
                                        <th>MD5 hash</th>
                                        <th>SHA1 hash</th>
                                    </tr>
                                    </tfoot>
                                    <tbody>
                                    <tr>
                                        <td class="user-firstName">albert</td>
                                        <td class="user-lastName">garcia</td>
                                        <td class="user-email">albert.garcia37@example.com</td>
                                        <td class="user-md5">9a26de5ea2dbe7fc5b6844128f307c38</td>
                                        <td class="user-sha1">68a90898cfc09508ed08aacf24482effe7f8f7c8</td>
                                    </tr>
                                    <tr>
                                        <td class="user-firstName">alexa</td>
                                        <td class="user-lastName">wallace</td>
                                        <td class="user-email">alexa.wallace18@example.com</td>
                                        <td class="user-md5">a3c4b614a1f072e0f968c2712a36323f</td>
                                        <td class="user-sha1">adfc8f0aec273eace2629141317c1eac53923f28</td>
                                    </tr>
                                    <tr>
                                        <td class="user-firstName">alexa</td>
                                        <td class="user-lastName">wallace</td>
                                        <td class="user-email">alexa.wallace18@example.com</td>
                                        <td class="user-md5">a3c4b614a1f072e0f968c2712a36323f</td>
                                        <td class="user-sha1">adfc8f0aec273eace2629141317c1eac53923f28</td>
                                    </tr>
                                    <tr>
                                        <td class="user-firstName">alexa</td>
                                        <td class="user-lastName">wallace</td>
                                        <td class="user-email">alexa.wallace18@example.com</td>
                                        <td class="user-md5">a3c4b614a1f072e0f968c2712a36323f</td>
                                        <td class="user-sha1">adfc8f0aec273eace2629141317c1eac53923f28</td>
                                    </tr>
                                    <tr>
                                        <td class="user-firstName">jack</td>
                                        <td class="user-lastName">cox</td>
                                        <td class="user-email">jack.cox73@example.com</td>
                                        <td class="user-md5">28e246ff038060bc335c0bf4925e5d51</td>
                                        <td class="user-sha1">8ad9a53bdd3b96cbc28efde629383954666ece67</td>
                                    </tr>
                                    <tr>
                                        <td class="user-firstName">meghan</td>
                                        <td class="user-lastName">taylor</td>
                                        <td class="user-email">meghan.taylor36@example.com</td>
                                        <td class="user-md5">573ce5969e9884d49d4fab77b09a306a</td>
                                        <td class="user-sha1">9621a244a447ec749598f8327560ff86095e2c26</td>
                                    </tr>
                                    <tr>
                                        <td class="user-firstName">amy</td>
                                        <td class="user-lastName">collins</td>
                                        <td class="user-email">amy.collins45@example.com</td>
                                        <td class="user-md5">f61f2f8157d9d632cc0b2bbc43d883f2</td>
                                        <td class="user-sha1">be842d987424dcb128bee9520682687bc2149284</td>
                                    </tr>
                                    <tr>
                                        <td class="user-firstName">nicole</td>
                                        <td class="user-lastName">lewis</td>
                                        <td class="user-email">nicole.lewis62@example.com</td>
                                        <td class="user-md5">e50081b0de0be206cd443d20094d3894</td>
                                        <td class="user-sha1">11615c74a29cc915c0802ae001d43b4cd96ced62</td>
                                    </tr>
                                    <tr>
                                        <td class="user-firstName">richard</td>
                                        <td class="user-lastName">graham</td>
                                        <td class="user-email">richard.graham65@example.com</td>
                                        <td class="user-md5">03764ebfde7010aa700c6e73f84ed00e</td>
                                        <td class="user-sha1">9286fa940279aa33e8e47ca7dd175324f333e4fa</td>
                                    </tr>
                                    <tr>
                                        <td class="user-firstName">brayden</td>
                                        <td class="user-lastName">hill</td>
                                        <td class="user-email">brayden.hill28@example.com</td>
                                        <td class="user-md5">a03490c03eaa102dadc25dca3cc6772b</td>
                                        <td class="user-sha1">6a53d618b92dcc6f23461cd323f993b210876602</td>
                                    </tr>
                                    <tr>
                                        <td class="user-firstName">amelia</td>
                                        <td class="user-lastName">wallace</td>
                                        <td class="user-email">amelia.wallace92@example.com</td>
                                        <td class="user-md5">7b6b45249743ad383e7f2e1fafd640fa</td>
                                        <td class="user-sha1">4003345f140d1f3af49b180ecdc8406755e3b472</td>
                                    </tr>
                                    <tr>
                                        <td class="user-firstName">melissa</td>
                                        <td class="user-lastName">scott</td>
                                        <td class="user-email">melissa.scott68@example.com</td>
                                        <td class="user-md5">5be977ac2dc6c7c07f8825de5f3c9926</td>
                                        <td class="user-sha1">34da1369d029a253a7586a8b582100180bba6441</td>
                                    </tr>
                                    <tr>
                                        <td class="user-firstName">elijah</td>
                                        <td class="user-lastName">hall</td>
                                        <td class="user-email">elijah.hall29@example.com</td>
                                        <td class="user-md5">21d9c57c1d2ae58cf12ece8a3470666b</td>
                                        <td class="user-sha1">a2916e062071972d7da46f818b685642b3cffcac</td>
                                    </tr>
                                    <tr>
                                        <td class="user-firstName">arianna</td>
                                        <td class="user-lastName">moore</td>
                                        <td class="user-email">arianna.moore76@example.com</td>
                                        <td class="user-md5">900e201a6aa7b0b1ce8218782d6142b6</td>
                                        <td class="user-sha1">c9d8f39e46aed665fa734cf1ca18b2f99cf6805f</td>
                                    </tr>
                                    <tr>
                                        <td class="user-firstName">nevaeh</td>
                                        <td class="user-lastName">griffin</td>
                                        <td class="user-email">nevaeh.griffin53@example.com</td>
                                        <td class="user-md5">2e036ecb177e0f732bcbc1b0984ffebd</td>
                                        <td class="user-sha1">044507c8314178f51f47bf2fd6e666a4139b6eef</td>
                                    </tr>
                                    <tr>
                                        <td class="user-firstName">sarah</td>
                                        <td class="user-lastName">smith</td>
                                        <td class="user-email">sarah.smith62@example.com</td>
                                        <td class="user-md5">de1b2a7baf7850243db71c4abd4e5a39</td>
                                        <td class="user-sha1">148627088915c721ccebb4c611b859031037e6ad</td>
                                    </tr>
                                    <tr>
                                        <td class="user-firstName">edward</td>
                                        <td class="user-lastName">wilson</td>
                                        <td class="user-email">edward.wilson63@example.com</td>
                                        <td class="user-md5">6673166b500d058c743b79c746c59243</td>
                                        <td class="user-sha1">2e8ae6ca8708f518d69cd1475017303ca0ae0080</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="../page/js/jquery.min.js" type="text/javascript"></script>
<script src="../reference/table/js/jquery.ba-throttle-debounce.min.js" type="text/javascript"></script>
<script type="text/javascript" src="../reference/table/js/jquery.stickysort.js"></script>
<script>
    $(function () {
        $('#basic-sort table').stickySort({sortable: true});
    });
</script>
</body>
</html>

