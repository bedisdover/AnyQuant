<!doctype html>
<html lang="zh" class="no-js">
<head>
    <link rel="stylesheet" href="http://libs.useso.com/js/bootstrap/3.2.0/css/bootstrap.min.css">
    <link rel='stylesheet' href='http://libs.useso.com/js/font-awesome/4.2.0/css/font-awesome.min.css'>
    <link href="../reference/emodal/dist/css/default.css" rel="stylesheet"/>
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="../reference/emodal/dist/js/eModal.js"></script>
    <%--<script src="reference/emodal/dist/"--%>
</head>
<style type="text/css">
    .pointer {
        cursor: pointer;
    }
</style>
<style>
    .cd-btn {
        background: #cdf199;
        color: #053644;
        border-radius: 10em;
        display: inline-block;
        padding: 0.6em 1.4em;
        /*font-size: 1.4rem;*/
        letter-spacing: .15em;
        box-shadow: 0 2px 10px rgba(0, 0, 0, 0.08);
        -webkit-transition: box-shadow .3s;
        transition: box-shadow .3s;
    }

    .cd-btn:hover {
        box-shadow: 0 6px 20px rgba(0, 0, 0, 0.2);
        cursor: pointer;
    }
</style>
<body>
<button value="test" class="cd-btn">test</button>
<section class="htmleaf-container">
    <section class="container-fluid" id="demo">
        <div class="container">
            <div class="row" itemprop="about">
                <div class="col-sm-1 text-center"></div>
                <div class="col-sm-2 text-center">
                    <div class="row">
                        <div class="col-sm-10 text-center">
                            <h3>Ajax</h3>
                            <p>You must get the message from a remote server? No problem!</p>
                            <i class="glyphicon glyphicon-cloud fa-5x pointer" title="Try me!"></i>
                        </div>
                    </div>
                </div>
                <div class="col-sm-1 text-center"></div>
            </div>
        </div>
        <br>
        <br>
        <!-- Default -->
    </section>
</section>

<script type="text/javascript">
    $(document).ready(function () {
        var iconPrefix = '.glyphicon-';
        $(iconPrefix + 'cloud').click(ajaxDemo);
        $(iconPrefix + 'comment').click(alertDemo);
        $(iconPrefix + 'ok').click(confirmDemo);
        $(iconPrefix + 'pencil').click(promptDemo);
        $(iconPrefix + 'screenshot').click(iframeDemo);
        // Demos
        function ajaxDemo() {
            var title = 'Ajax modal';
            var params = {
                buttons: [
                    {text: 'Close', close: true, style: 'danger'},
                    {text: 'New content', close: false, style: 'success', click: ajaxDemo}
                ],
                size: eModal.size.lg,
                title: title,
                url: 'http://maispc.com/app/proxy.php?url=http://loripsum.net/api/' + Math.floor((Math.random() * 7) + 1) + '/short/ul/bq/prude/code/decorete'
            };

            return eModal
                    .ajax(params)
                    .then(function () {
                    });
        }

        function alertDemo() {
            var title = 'Alert modal';
            return eModal
                    .alert('You welcome! Want clean code ?', title)
                    .then(function () {
                    });
        }

        function confirmDemo() {
            var title = 'Confirm modal callback feedback';
            return eModal
                    .confirm('It is simple enough?', 'Confirm modal')
                    .then(
                            function (/* DOM */) {
                            },
                            function (/*null*/) {
                            });
        }

        function iframeDemo() {
            var title = 'Insiders';
            return eModal
                    .iframe('https://player.vimeo.com/video/151579457', title)
                    .then(function () {
                    });
        }

        function promptDemo() {
            var title = 'Prompt modal callback feedback';
            return eModal
                    .prompt({size: eModal.size.sm, message: 'What\'s your name?', title: title})
                    .then();
        }
    });
</script>

</body>
</html>