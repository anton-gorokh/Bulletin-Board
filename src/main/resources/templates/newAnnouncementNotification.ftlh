<!DOCTYPE html>
<html>
<style>
			body {
				font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif;
				color: #777;
			}

			body h1 {
				font-weight: 300;
				margin-bottom: 0px;
				padding-bottom: 0px;
				color: #000;
			}

			body h3 {
				font-weight: 300;
				margin-top: 10px;
				margin-bottom: 20px;
				font-style: italic;
				color: #555;
			}

			body a {
				color: #06f;
			}

			.announcement {
				max-width: 800px;
				margin: auto;
				padding: 30px;
				border: 1px solid #eee;
				box-shadow: 0 0 10px rgba(0, 0, 0, 0.15);
				font-size: 16px;
				line-height: 24px;
				font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif;
				color: #555;
			}

			.announcement table {
				width: 100%;
				line-height: inherit;
				text-align: left;
				border-collapse: collapse;
			}

			.announcement table td {
				padding: 5px;
				vertical-align: top;
			}

			.announcement table tr td:nth-child(2) {
				text-align: right;
			}

			.announcement table tr.top table td {
				padding-bottom: 20px;
			}

			.announcement table tr.top table td.title {
				font-size: 45px;
				line-height: 45px;
				color: #333;
			}

			.announcement table tr.information table td {
				padding-bottom: 40px;
			}

			.announcement table tr.heading td {
				background: #eee;
				border-bottom: 1px solid #ddd;
				font-weight: bold;
			}

			.announcement table tr.details td {
				padding-bottom: 20px;
			}

			.announcement table tr.item td {
				border-bottom: 1px solid #eee;
			}

			.announcement table tr.item.last td {
				border-bottom: none;
			}

			.announcement table tr.total td:nth-child(2) {
				border-top: 2px solid #eee;
				font-weight: bold;
			}

			@media only screen and (max-width: 600px) {
				.announcement table tr.top table td {
					width: 100%;
					display: block;
					text-align: center;
				}

				.announcement table tr.information table td {
					width: 100%;
					display: block;
					text-align: center;
				}
			}

</style>
</head>
<body>
<div class="announcement">
    <table>
        <tbody>
        <tr class="title">
            <td colspan="2">
                <table>
                    <tbody>
                    <tr>
                        <td style="text-align:center">
                            The new Announcement of type "${category}" was created
                        </td>
                    </tbody>
                </table>
            </td>
        </tr>

        <tr class="heading">
            <td>Author</td>

            <td>Contact info</td>
        </tr>

        <tr class="details">
            <td>${author.fullName}</td>

            <td>${contactInfo}</td>
        </tr>

        <tr class="heading">
            <td>Announcement</td>

            <td>Info</td>
        </tr>

        <tr class="item">
            <td>Category</td>

            <td>${category}</td>
        </tr>

        <tr class="item">
            <td>Pay</td>

            <td>${pay}</td>
        </tr>

        <tr class="item">
            <td style="max-width: 10px">Name</td>

            <td style="word-break: break-all; overflow-wrap: break-word;">${announcement.name}</td>
        </tr>
        </tbody>
    </table>
    <br>
    <tr>
        <td class="information" style="line-height:1.5em;">${announcement.text}</td>
    </tr>
</div>
</body>
</html>