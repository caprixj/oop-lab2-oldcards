<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">
    <xsl:output method="xml" indent="yes"/>
    <xsl:template match="/Cards">
        <Cards>
            <xsl:for-each-group select="OldCard" group-by="Type">
                <xsl:element name="{current-grouping-key()}">
                    <xsl:for-each select="current-group()">
                        <xsl:copy-of select="."/>
                    </xsl:for-each>
                </xsl:element>
            </xsl:for-each-group>
        </Cards>
    </xsl:template>
</xsl:stylesheet>
