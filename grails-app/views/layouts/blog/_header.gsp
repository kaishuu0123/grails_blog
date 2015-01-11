<!-- Static navbar -->
<div class="header">
    <nav role="navigation" class="navbar navbar-default">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button data-target=".navbar-ex5-collapse" data-toggle="collapse" class="navbar-toggle collapsed" type="button">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${g.createLink(controller: 'posts')}"><blog:settings name="blogTitle" /></a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="navbar-collapse navbar-ex5-collapse collapse" style="height: 1px;">
            <ul class="nav navbar-nav pull-right pills">
                <li <g:if test="${!params.slug}">class="active"</g:if>><a href="${g.createLink(controller: "posts")}">Home</a></li>
                <li><a href="${g.createLink(controller: "Admin")}">Admin</a></li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </nav>
</div>