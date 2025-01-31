var nodes = new vis.DataSet([
    {id: 1, label: "Banco de Loja", color: {background: "#A5D6A7", border: "#4CAF50"}, size: 30},
    {id: 2, label: "Banco Guayaquil", color: {background: "#90CAF9", border: "#2196F3"}, size: 30},
    {id: 3, label: "Banco del Austro", color: {background: "#FFCC80", border: "#FF9800"}, size: 30},
    {id: 4, label: "Banco de Pichincha", color: {background: "#CE93D8", border: "#9C27B0"}, size: 30},
    {id: 5, label: "Banco de Machala", color: {background: "#FFAB91", border: "#FF5722"}, size: 30},
    {id: 6, label: "BanEcuador", color: {background: "#9FA8DA", border: "#3F51B5"}, size: 30},
    {id: 7, label: "Produbanco", color: {background: "#BCAAA4", border: "#795548"}, size: 30},
    {id: 8, label: "Banco Bolivariano", color: {background: "#B0BEC5", border: "#607D8B"}, size: 30},
    {id: 9, label: "Banco Internacional", color: {background: "#F48FB1", border: "#E91E63"}, size: 30},
    {id: 10, label: "Banco Solidario", color: {background: "#80DEEA", border: "#00BCD4"}, size: 30}
]);

var edges = new vis.DataSet([
    {from: 1, to: 2, label: "21455.32", arrows: "to", color: {color: "#D32F2F"}},
    {from: 1, to: 3, label: "30500.78", arrows: "to", color: {color: "#1976D2"}},
    {from: 2, to: 4, label: "19800.45", arrows: "to", color: {color: "#388E3C"}},
    {from: 2, to: 5, label: "25000.67", arrows: "to", color: {color: "#FFA000"}},
    {from: 3, to: 6, label: "45200.99", arrows: "to", color: {color: "#7B1FA2"}},
    {from: 3, to: 7, label: "12400.67", arrows: "to", color: {color: "#E64A19"}},
    {from: 4, to: 8, label: "36500.23", arrows: "to", color: {color: "#00838F"}},
    {from: 5, to: 9, label: "47800.88", arrows: "to", color: {color: "#D81B60"}},
    {from: 6, to: 10, label: "50000.00", arrows: "to", color: {color: "#4A148C"}}
]);

var container = document.getElementById('mynetwork');
var data = { nodes: nodes, edges: edges };
var options = {
    nodes: {
        shape: "dot",
        font: {
            size: 16,
            color: "black"
        },
        borderWidth: 2,
        shadow: false
    },
    edges: {
        width: 2,
        arrows: {
            to: {enabled: true, scaleFactor: 1.2}
        },
        font: {
            align: "middle",
            color: "black",
            size: 14
        }
    },
    physics: {
        enabled: true
    }
};
var network = new vis.Network(container, data, options);
