clear
close all
load A2_data.mat
%% TASK E1

K = 5;
d = 2;
norm_mean = mean(train_data_01,2);

norm_data = (train_data_01 - norm_mean);

[U, S, V] = svd(norm_data);
%%
proj = U(:,1:d)'*norm_data;
%% TASK E2
[y,C] = K_means_clustering(proj,K);
%%
figure
hold on
c_map = parula(12);
proj = proj';
for i = 1:K
    plot(proj(y==i,1), proj(y==i,2), '*', 'Color', c_map(2*i,:))
end
proj = proj';

%%
for i = 1:K
    scatter(C(1,:),C(2,:), 200, 'black', 'filled')
end
axis([-10, 10, -10, 10])

%% TASK E3
figure
im_zero = C(1,1)*U(:, 1) + C(2,1)*U(:, 2);
imshow(reshape(im_zero, 28, 28));
figure
im_one = C(1,2)*U(:, 1) + C(2,2)*U(:, 2);
imshow(reshape(im_one, 28, 28));

%% TASK E4
labels = K_means_classification(train_labels_01,y,K,C);
%%
Legend = cell(K,1);
counter = 1;
for iter=1:K
    if ~isnan(labels(iter))
       Legend{counter}=strcat('Label: ', num2str(labels(iter)));
       counter = counter + 1;
    end
end
legend(Legend(1:counter-1))

%% Correct and fail for training data
correct = zeros(K,1);
fail = zeros(K,1);

for i = 1:K
    correct(i) = sum(train_labels_01(y == i) == labels(i));
    fail(i) = sum(train_labels_01(y == i) ~= labels(i));
end

%% Table for Training data
M = zeros(K,5);
M(:,1) = 1:K;
for i = 1:K
    for j = 1:2
        M(i,j+1) = sum(train_labels_01(y == i) == j-1);
    end
end
M(:,4) = labels;
M(:,5) = fail;
Ntrain = size(proj,2);
sumMisclassified = sum(fail);
failRate = 100*sumMisclassified/Ntrain;


%% Correct and fail for training data   (GL�M INTE ATT �NDRA ALLT TILL TEST FR�N B�RJAN)
correct = zeros(K,1);
fail = zeros(K,1);

for i = 1:K
    correct(i) = sum(test_labels_01(y == i) == labels(i));
    fail(i) = sum(test_labels_01(y == i) ~= labels(i));
end

%% Table for Test data
M = zeros(K,3 + K);
M(:,1) = 1:K;
for i = 1:K
    for j = 1:K
        M(i,j+1) = sum(test_labels_01(y == i) == j-1);
    end
end
M(:,2+K) = labels;
M(:,3+K) = fail;
Ntrain = size(proj,2);
sumMisclassified = sum(fail);
failRate = 100*sumMisclassified/Ntrain;


%% TASK E6 - SVM
%Training data:
model = fitcsvm(train_data_01', train_labels_01);
pred_value_train = predict(model, train_data_01');
correct_train = sum(pred_value == train_labels_01);

% Test data:
pred_value = predict(model, test_data_01');
correct_test = sum(pred_value == test_labels_01);

%% TASK E7 
sigma = 1;
beta = sqrt(1/sigma^2);
model = fitcsvm(train_data_01', train_labels_01, 'KernelFunction', 'gaussian', 'KernelScale', beta);
%% Training data
pred_value_train = predict(model, train_data_01');
correct_train = sum(pred_value_train == train_labels_01);
%% Test data
pred_value_test = predict(model, test_data_01');
correct_test = sum(pred_value_test == test_labels_01);

%% TASK E8
