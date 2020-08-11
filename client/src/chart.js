// ========================= GRAPH CHART SECTION ==================================

let AccPerfChart = {
    render: function (chartObj) {
        let margin = chartObj.margin;

        const width = parseInt(chartObj.chart.node().offsetWidth) - margin.left - margin.right;
        const height = parseInt(width * 0.4) - margin.top - margin.bottom;

        chartObj.svg.attr('width', width + margin.left + margin.right)
                .attr('height', height + margin.top + margin.bottom);

        chartObj.x.range([0, width]);
        chartObj.y.range([height, 0]);

        chartObj.plot.select('.axis.x')
            .attr('transform', `translate(0, ${height})`)
            .call(chartObj.xAxis)
            .attr('font-weight', 100)
            .attr('font-size', "1.2em")
            .select('.domain').remove();

        chartObj.plot.select('.axis.y')
            .call(chartObj.yAxis)
            .attr('font-weight', 100)
            .attr('font-size', "1.2em")
            .call(g => g.select('.tick:last-of-type text').clone()
                .attr('x', 0)
                .attr('text-anchor', 'start')
                .attr('font-weight', 300)
                .text('€'))
            .select('.domain').remove();

        chartObj.plot.select('.baseline')
            .attr('x1', 0)
            .attr('x2', width)
            .attr('y1', chartObj.y(0))
            .attr('y2', chartObj.y(0))
            .attr('fill', 'none')
            .attr('stroke', '#a1afc3')
            .attr('stroke-width', '1px')
            .attr('shape-rendering', 'crispEdges')
            .attr('stroke-dasharray', '3, 3')

        const path = chartObj.plot.selectAll('path')
            .attr('d', d => chartObj.line(d.values))
            .attr('stroke', d => chartObj.colour(d.name))
            .attr('id', (d, i) => `line-${d.name}`)

        path.each((d, i) => {
            const sel = d3.select(`#line-${d.name}`);
            const length = sel.node().getTotalLength();
            sel.attr('stroke-dasharray', `${length} ${length}`)
                .attr('stroke-dashoffset', length)
                .transition()
                .duration(5000)
                .attr('stroke-dashoffset', 0)
        })

        chartObj.plot.selectAll('.line-label')
            .attr('class', 'line-label')
            .attr('x', 10)
            .attr('y', 30)
            .attr('dy', '.35em')
            .attr('fill', d => chartObj.colour(d.name))
            .attr('font-weight', 400)
            .attr('font-size', "1.5em")
            .text(d => d.name + ' ' + d.value.value + ' ' + new Date(d.value.date).toISOString().substring(0, 10))
            .attr('opacity', 0)
            .transition()
            .delay(4000)
            .duration(200)
            .attr('opacity', 1);

        chartObj.plot.selectAll('.circle')
        // this move the circle to the end of the line.
            .attr('transform', d => {
                return `translate(${chartObj.x(d.value.date)}, ${chartObj.y(d.value.value)})`;
            })
            .attr('class', 'circle')
            .attr('x', 0)
            .attr('opacity', 0)
            .transition()
            .delay(4000)
            .duration(200)
            .attr('opacity', 1);

        //glow filter
    },

    bindData: function (chartData, chartObj) {
        const data = [chartData]

        chartObj.colour.domain([chartData.name]);

        // screen dimensions
        chartObj.x.domain(d3.extent(chartData.values, d => d.date));
        chartObj.y.domain([
            d3.min(data, c => d3.min(c.values, v => v.value)) / 1.05,
            d3.max(data, c => d3.max(c.values, v => v.value)) * 1.1
        ]).nice();

        // bind data to DOM elements
        const $lines = chartObj.plot.append('g')
            .attr('class', 'lines')
            .selectAll('.line')
            .data(data)
            .enter()
            .append('g')
            .attr('class', 'line')
            .attr('fill', 'none')
            .attr('stroke-width', '2px')


        $lines.append('path')
            .attr('class', 'path')

        $lines.append('text')
            .datum(d => {
                return {
                    name: d.name,
                    value: d.values[d.values.length - 1]
                }
            })
            .attr('class', 'line-label')
            .attr('opacity', 0)

        $lines.append('circle')
            .datum(d => {
                return {
                    name: d.name,
                    value: d.values[d.values.length - 1]
                }
            })
            .attr('class', 'circle')
            .attr('r', 4)
            .attr('fill', '#ffffff')
            .style('stroke', d => chartObj.colour(d.name))
            .style('stroke-width', 2)

        chartObj.plot.append('g')
            .attr('class', 'axis x');

        chartObj.plot.append('g')
            .attr('class', 'axis y');

        chartObj.plot.append('line')
            .attr('class', 'baseline')

        this.render(chartObj);
    },

}
