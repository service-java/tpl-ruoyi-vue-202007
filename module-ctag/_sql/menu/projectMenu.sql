-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('特征标记工程', '2000', '1', 'project', 'ctag/project/index', 1, 'C', '0', '0', 'ctag:project:list', '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '特征标记工程菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('特征标记工程查询', @parentId, '1',  '#', '', 1,  'F', '0',  '0', 'ctag:project:query',        '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('特征标记工程新增', @parentId, '2',  '#', '', 1,  'F', '0',  '0', 'ctag:project:add',          '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('特征标记工程修改', @parentId, '3',  '#', '', 1,  'F', '0',  '0', 'ctag:project:edit',         '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('特征标记工程删除', @parentId, '4',  '#', '', 1,  'F', '0',  '0', 'ctag:project:remove',       '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('特征标记工程导出', @parentId, '5',  '#', '', 1,  'F', '0',  '0', 'ctag:project:export',       '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');
