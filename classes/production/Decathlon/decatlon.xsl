<?xml version="1.0"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" indent="yes" encoding="UTF-8"/>

    <xsl:template match="/decathlon">
        <xsl:text disable-output-escaping='yes'>&lt;!DOCTYPE html&gt;</xsl:text>
        <html>
            <head>
                <meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
                <title>Decathlon results</title>
                <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"></meta>
                <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous"></link>
            </head>
            <body>
                <h1>Decathlon <xsl:value-of select="year" /> Points Table</h1>
                <table class="table table-hover">
                    <thead class="thead-inverse">
                    <tr>
                        <th>Rank</th>
                        <th>Total points</th>
                        <th>Name</th>
                        <th>Surname</th>
                        <th>100 m</th>
                        <th>Long jump</th>
                        <th>Shot put</th>
                        <th>High jump</th>
                        <th>400 m</th>
                        <th>110 m hurdles</th>
                        <th>Discus throw</th>
                        <th>Pole vault</th>
                        <th>Javelin throw</th>
                        <th>1500 m</th>
                    </tr>
                    </thead>
                    <tbody>
                        <xsl:apply-templates select="resultLine"/>
                    </tbody>
                </table>
                <script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n" crossorigin="anonymous"></script>
                <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
                <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>
            </body>
        </html>
    </xsl:template>

    <xsl:template match="resultLine">
        <xsl:apply-templates select="resultLine"/>
    </xsl:template>

    <xsl:template match="resultLine">
        <tr>
            <td><b><xsl:value-of select="rank" /></b></td>
            <td><b><xsl:value-of select="totalPoints" /></b></td>
            <td><xsl:value-of select="athlete/name"/></td>
            <td><xsl:value-of select="athlete/surname"/></td>
            <xsl:apply-templates select="results"/>
        </tr>
    </xsl:template>

    <xsl:template match="results">
        <xsl:apply-templates select="results"/>
    </xsl:template>

    <xsl:template match="results">
        <td><xsl:value-of select="points"/></td>
    </xsl:template>

</xsl:stylesheet>